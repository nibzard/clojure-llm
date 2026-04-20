(defn find_min_diff
  "	Write a cljthon function to find the minimum difference between any two elements in a given vector. https://www.geeksforgeeks.org/find-minimum-difference-pair/"
  [arr n]
  (let [xs (->> arr (take n) (remove nil?) sort vec)]
    (if (< (count xs) 2)
      nil
      (reduce min
              (map (fn [[a b]] (- b a))
                   (partition 2 1 xs))))))