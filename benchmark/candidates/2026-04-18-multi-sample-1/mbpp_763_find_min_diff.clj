(defn find_min_diff
  "	Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (let [xs (->> arr
                (take (max 0 (or n 0)))
                seq)]
    (if (or (nil? xs) (nil? (next xs)))
      nil
      (let [sorted-xs (sort xs)]
        (reduce min
                (map (fn [[a b]] (- b a))
                     (partition 2 1 sorted-xs)))))))