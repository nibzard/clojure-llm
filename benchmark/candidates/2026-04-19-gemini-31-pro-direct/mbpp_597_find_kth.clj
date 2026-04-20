(defn find_kth
  "Write a function to find kth element from the given two sorted vectors."
  [arr1 arr2 k]
  (nth (sort (concat arr1 arr2)) (dec k)))