(defn shell_sort
  "	Write a function to sort the given vector by using shell sort."
  [my_list]
  (let [v (vec (or my_list []))
        n (count v)]
    (if (<= n 1)
      v
      (letfn [(insert-gap [arr idx gap]
                (let [temp (nth arr idx)]
                  (loop [a arr
                         j idx]
                    (if (and (>= j gap) (> (nth a (- j gap)) temp))
                      (recur (assoc a j (nth a (- j gap))) (- j gap))
                      (assoc a j temp)))))
              (gap-pass [arr gap]
                (loop [a arr
                       i gap]
                  (if (< i n)
                    (recur (insert-gap a i gap) (inc i))
                    a)))]
        (loop [arr v
               gap (quot n 2)]
          (if (zero? gap)
            arr
            (recur (gap-pass arr gap) (quot gap 2))))))))