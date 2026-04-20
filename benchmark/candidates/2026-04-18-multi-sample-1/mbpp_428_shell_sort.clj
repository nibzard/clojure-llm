(defn shell_sort
  "	Write a function to sort the given vector by using shell sort."
  [my_list]
  (let [v (vec (or my_list []))
        n (count v)]
    (if (<= n 1)
      v
      (loop [arr v
             gap (quot n 2)]
        (if (zero? gap)
          arr
          (let [arr'
                (loop [a arr
                       i gap]
                  (if (>= i n)
                    a
                    (let [temp (nth a i)]
                      (loop [a2 a
                             j i]
                        (if (and (>= j gap)
                                 (> (nth a2 (- j gap)) temp))
                          (recur (assoc a2 j (nth a2 (- j gap)))
                                 (- j gap))
                          (recur
                           (assoc a2 j temp)
                           n))))))
                arr'' (loop [a arr'
                             i gap]
                        (if (>= i n)
                          a
                          (let [temp (nth a i)]
                            (loop [a2 a
                                   j i]
                              (if (and (>= j gap)
                                       (> (nth a2 (- j gap)) temp))
                                (recur (assoc a2 j (nth a2 (- j gap)))
                                       (- j gap))
                                (recur (assoc a2 j temp) n))))))]
            (recur arr'' (quot gap 2)))))))