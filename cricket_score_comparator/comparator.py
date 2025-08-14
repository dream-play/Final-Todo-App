import requests
from bs4 import BeautifulSoup
import time

def get_score(url, selector):
    """Fetches the score from a given URL using a CSS selector."""
    try:
        # Some websites might block requests without a user-agent
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'
        }
        response = requests.get(url, headers=headers)
        response.raise_for_status()
        soup = BeautifulSoup(response.text, 'html.parser')
        score_element = soup.select_one(selector)
        return score_element.text.strip() if score_element else "Score not found"
    except requests.exceptions.RequestException as e:
        return f"Error fetching {url}: {e}"
    except Exception as e:
        return f"An error occurred: {e}"

def main():
    """Main function to run the score comparator."""
    print("--- Cricket Score Comparator ---")
    print("This tool compares score updates from multiple websites to see which one is faster.")
    print("You will need to provide the URL of the match and the CSS selector for the score element.\n")
    print("How to find the CSS selector:")
    print("1. Open the match page in your browser (e.g., Chrome, Firefox).")
    print("2. Right-click on the score you want to track and select 'Inspect'.")
    print("3. The developer tools will open, highlighting the HTML element for the score.")
    print("4. Right-click on the highlighted element, go to 'Copy', and then 'Copy selector'.")
    print("5. Paste this selector when prompted by the tool.\n")

    sites = []
    while True:
        url = input("Enter the URL for a cricket match (or type 'done' to start): ")
        if url.lower() == 'done':
            if len(sites) < 2:
                print("Please provide at least two websites to compare.")
                continue
            break

        selector = input(f"Enter the CSS selector for the score on {url}: ")
        sites.append({"url": url, "selector": selector})
        print("Site added.\n")

    if not sites:
        print("No websites provided. Exiting.")
        return

    previous_scores = {site['url']: "" for site in sites}
    update_log = []
    first_to_update_counts = {site['url']: 0 for site in sites}

    try:
        while True:
            updated_sites_in_cycle = {}
            for site in sites:
                url = site['url']
                selector = site['selector']
                current_score = get_score(url, selector)

                if "Error" in current_score or current_score == "Score not found":
                    # Optionally print an error or log it
                    # print(f"Could not retrieve score from {url}: {current_score}")
                    continue

                if current_score != previous_scores[url]:
                    timestamp = time.time()
                    updated_sites_in_cycle[url] = (current_score, timestamp)
                    previous_scores[url] = current_score

            if updated_sites_in_cycle:
                # Sort the updated sites by timestamp to find the fastest one
                fastest_first = sorted(updated_sites_in_cycle.items(), key=lambda item: item[1][1])

                # The first one in the sorted list is the fastest for this update cycle
                fastest_url, (new_score, timestamp) = fastest_first[0]
                first_to_update_counts[fastest_url] += 1

                log_entry = f"Update detected! Score: {new_score}. Fastest: {fastest_url}"
                update_log.append(log_entry)
                print(log_entry)

                # Print all updates in this cycle
                for url, (score, _) in fastest_first:
                    print(f"  - {url} updated to {score}")

                print("\n--- Leaderboard ---")
                for url, count in sorted(first_to_update_counts.items(), key=lambda item: item[1], reverse=True):
                    print(f"{url}: {count} times first")
                print("-------------------\n")

            time.sleep(10) # Wait for 10 seconds before checking again

    except KeyboardInterrupt:
        print("\n--- Comparison Stopped ---")
        print("Final Leaderboard:")
        for url, count in sorted(first_to_update_counts.items(), key=lambda item: item[1], reverse=True):
            print(f"{url}: {count} times first")
        print("\nUpdate Log:")
        for entry in update_log:
            print(entry)
        print("------------------------\n")

if __name__ == "__main__":
    main()
