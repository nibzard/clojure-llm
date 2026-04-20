(defn max_subarray_product
  "	Write a function to find the maximum product subvector of the given vector."
  [arr]
  (when (seq arr)
    (let [nums (vec arr)]
      (loop [i 1
             max-ending (nums 0)
             min-ending (nums 0)
             best (nums 0)]
        (if (= i (count nums))
          best
          (let [x (nums i)
                candidates [x (* x max-ending) (* x min-ending)]
                new-max (apply max candidates)
                new-min (apply min candidates)]
            (recur (inc i) new-max new-min (max best new-max))))))))