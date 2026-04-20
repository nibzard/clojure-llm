(defn find_min_diff
  "	Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (let [xs (->> arr
                (take (max 0 (or n 0)))
                sort
                vec)]
    (when (>= (count xs) 2)
      (->> (map - (subvec xs 1) (subvec xs 0 (dec (count xs))))
           (apply min)))))