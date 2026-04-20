(defn shell_sort
  "	Write a function to sort the given vector by using shell sort."
  [my_list]
  (let [v (vec my_list)
        n (count v)]
    (loop [arr v
           gap (quot n 2)]
      (if (zero? gap)
        arr
        (let [arr' (loop [arr arr
                          i gap]
                     (if (>= i n)
                       arr
                       (let [temp (arr i)
                             arr2 (loop [arr arr
                                         j i]
                                    (if (and (>= j gap)
                                             (> (arr (- j gap)) temp))
                                      (recur (assoc arr j (arr (- j gap)))
                                             (- j gap))
                                      (assoc arr j temp)))]
                         (recur arr2 (inc i)))))]
          (recur arr' (quot gap 2)))))))