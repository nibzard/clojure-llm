(defn find_min_diff
  "Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (if (< n 2)
    0
    (let [sorted-arr (sort arr)
          diffs (map #(- %2 %1) sorted-arr (rest sorted-arr))]
      (apply min diffs))))