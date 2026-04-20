(defn find_min_diff
  "	Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (let [xs (->> arr
                (take (or n 0))
                sort
                vec)]
    (when (>= (count xs) 2)
      (reduce min
              (map #(- (xs (inc %)) (xs %))
                   (range (dec (count xs))))))))