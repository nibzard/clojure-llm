(defn maximum
  "Given a vector arr of integers and a positive integer k, return a sorted list 
  of length k with the maximum k numbers in arr.
  Example 1:
  >>> (maximum [-3 -4 5] 3)
  [-4 -3 5]
  Example 2:
  >>> (maximum [4 -4 4] 2)
  [4 4]
  Example 3:
  >>> (maximum [-3 2 1 2 -1 -2 1] 1)
  [2]
  Note:
      1. The length of the vector will be in the range of [1, 1000].
      2. The elements in the vector will be in the range of [-1000, 1000].
      3. 0 <= k <= len(arr)"
  [arr k]
  (->> arr
       sort
       (take-last k)
       vec))