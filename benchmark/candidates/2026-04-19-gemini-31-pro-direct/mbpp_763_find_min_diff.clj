(defn find_min_diff
  "	Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (if (< n 2)
    0
    (let [sorted (sort arr)]
      (reduce min (map - (rest sorted) sorted)))))