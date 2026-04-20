(defn maximum-unique
  "Given a vector arr of integers and a positive integer k, return a sorted vector
  of the largest k DISTINCT numbers in arr.

  If arr has fewer than k distinct numbers, return all distinct numbers sorted ascending.

  Examples:
  >>> (maximum-unique [-3 -4 5] 3)
  [-4 -3 5]
  >>> (maximum-unique [4 -4 4] 2)
  [-4 4]
  >>> (maximum-unique [-3 2 1 2 -1 -2 1] 3)
  [1 2 -1]

  Notes:
  1. Input may contain duplicates.
  2. The result must be sorted ascending.
  3. Return a vector."
  [arr k])