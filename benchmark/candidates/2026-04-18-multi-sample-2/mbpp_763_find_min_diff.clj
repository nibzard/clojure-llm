(defn find_min_diff
  "	Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (let [xs (take (max 0 (or n 0)) (or arr []))]
    (when (>= (count xs) 2)
      (->> xs
           sort
           (partition 2 1)
           (map (fn [[a b]] (Math/abs (long (- b a)))))
           (reduce min)))))