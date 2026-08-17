You are given an array of integers compute_loads representing the processing load of consecutive servers in a data center. You are also given an integer k, representing the size of a server cluster. A cluster is any contiguous subarray of exactly k servers.

To ensure stability, the "stability score" of any server cluster is defined as the sum of the compute loads in the cluster, multiplied by the minimum compute load in that same cluster.

Write a function that finds and returns the maximum stability score among all possible clusters of size k.