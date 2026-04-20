(defn shell_sort
  "	Write a function to sort the given vector by using shell sort."
  [my_list]
  (let [v (vec (or my_list []))
        n (count v)]
    (loop [arr v
           gap (quot n 2)]
      (if (zero? gap)
        arr
        (recur
         (loop [a arr
                i gap]
           (if (>= i n)
             a
             (let [temp (nth a i)]
               (recur
                (loop [b a
                       j i]
                  (if (and (>= j gap)
                           (> (nth b (- j gap)) temp))
                    (recur (assoc b j (nth b (- j gap))) (- j gap))
                    (assoc b j temp)))
                (inc i)))))
         (quot gap 2))))))