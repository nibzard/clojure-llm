(defn shell_sort
  "	Write a function to sort the given vector by using shell sort."
  [my_list]
  (let [v (vec (or my_list []))
        n (count v)]
    (if (<= n 1)
      v
      (letfn [(gap-insert [arr gap i]
                (let [temp (nth arr i)]
                  (loop [a arr
                         j i]
                    (if (and (>= j gap) (> (nth a (- j gap)) temp))
                      (recur (assoc a j (nth a (- j gap))) (- j gap))
                      (assoc a j temp)))))
              (gap-pass [arr gap]
                (loop [a arr
                       i gap]
                  (if (< i n)
                    (recur (gap-insert a gap i) (inc i))
                    a)))]
        (loop [arr v
               gap (quot n 2)]
          (if (zero? gap)
            arr
            (recur (gap-pass arr gap) (quot gap 2))))))))