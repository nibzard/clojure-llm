(defn find_min_diff
  "	Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (let [xs (->> arr
                (take (or n 0))
                (remove nil?)
                sort)]
    (when (>= (count xs) 2)
      (reduce min
              (map (fn [[a b]] (- b a))
                   (partition 2 1 xs))))))