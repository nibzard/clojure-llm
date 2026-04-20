(defn find_min_diff
  "	Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (let [xs (->> arr
                (take (max 0 (or n 0)))
                (remove nil?)
                sort)]
    (if (< (count xs) 2)
      nil
      (->> (map - (rest xs) xs)
           (apply min)))))