(defn find_min_diff
  "Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (let [xs (take n (sort arr))]
    (if (< (count xs) 2)
      nil
      (reduce min
              (map #(Math/abs ^long (- (long %2) (long %1)))
                   xs
                   (rest xs))))))