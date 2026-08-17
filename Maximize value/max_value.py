def find_max(server_list, k):
    max = 0
    server_sets = [server_list[i:i+k] for i in range(len(server_list) - (k-1))]

    for set in server_sets:
        minimum = min(set)
        summation = sum(set)
        set_total = minimum * summation
        if set_total > max:
            max = set_total


    return max

from collections import deque

def find_max_optimal(compute_loads, k):
    if not compute_loads or k == 0:
        return 0
        
    max_score = 0
    current_sum = 0
    
    # Stores INDICES, keeping the values strictly increasing.
    # deque[0] will always be the index of the minimum value in the current window.
    min_queue = deque()
    
    for i in range(len(compute_loads)):
        # 1. Update the sliding window sum
        current_sum += compute_loads[i]
        
        # 2. Maintain the monotonic queue for the minimum
        # Remove any indices from the back whose values are >= the current value.
        # They can never be the minimum as long as the current, smaller value is in the window.
        while min_queue and compute_loads[min_queue[-1]] >= compute_loads[i]:
            min_queue.pop()
        
        # Add the current index
        min_queue.append(i)
        
        # 3. Process the window once it reaches size K
        if i >= k - 1:
            # The minimum value is always at the front of the queue
            current_min = compute_loads[min_queue[0]]
            
            # Calculate and update max score
            max_score = max(max_score, current_sum * current_min)
            
            # 4. Slide the window: remove the leftmost element going out of bounds
            outgoing_index = i - k + 1
            current_sum -= compute_loads[outgoing_index]
            
            # If the element falling out of the window is our current minimum, 
            # remove it from the front of the queue
            if min_queue[0] == outgoing_index:
                min_queue.popleft()
                
    return max_score

# Test the optimized code
print(find_max_optimal([2, 1, 3, 4, 5, 2], 3))  # Output: 36

compute_loads = [2, 1, 3, 4, 5, 2]
size = 3
print(find_max(compute_loads, 3))