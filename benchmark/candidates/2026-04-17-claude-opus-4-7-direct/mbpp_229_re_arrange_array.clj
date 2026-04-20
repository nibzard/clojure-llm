(defn re_arrange_array
  "Write a function that takes in a vector and an integer n, and re-arranges the first n elements of the given vector so that all negative elements appear before positive ones, and where the relative order among negative and positive elements is preserved."
  [arr n]
  (let [sub-vec (subvec arr 0 n)
        rest-vec (subvec arr n)
        partitioned (partition-by #(< % 0) sub-vec)
        sorted-partitions (sort-by (fn [p] (if (neg? (first p)) 0 1)) partitioned)
        new-sub-vec (apply concat sorted-partitions)]
    (into rest-vec new-sub-vec)))