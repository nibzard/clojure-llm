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
                       (let [temp (arr i)]
                         (loop [arr arr
                                j i]
                           (if (and (>= j gap)
                                    (> (arr (- j gap)) temp))
                             (recur (assoc arr j (arr (- j gap)))
                                    (- j gap))
                             (recur (assoc arr j temp)
                                    (inc i)))))))]
          (recur arr' (quot gap 2)))))))