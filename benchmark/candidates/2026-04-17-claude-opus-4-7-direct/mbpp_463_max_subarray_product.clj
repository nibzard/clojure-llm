(defn max_subarray_product
  "Write a function to find the maximum product subvector of the given vector."
  [arr]
  (if (empty? arr)
    0
    (loop [nums (rest arr)
           current-max (first arr)
           current-min (first arr)
           global-max (first arr)]
      (if (empty? nums)
        global-max
        (let [x (first nums)
              ;; Calculate candidates for the new max and min
              ;; We consider x itself (starting a new subarray)
              ;; or extending the previous subarray
              candidates [x
                          (* x current-max)
                          (* x current-min)]
              new-max (apply max candidates)
              new-min (apply min candidates)]
          (recur (rest nums)
                 new-max
                 new-min
                 (max global-max new-max)))))))