(defn shell_sort
  "	Write a function to sort the given vector by using shell sort."
  [my_list]
  (let [v (vec (or my_list []))
        n (count v)]
    (loop [gap (quot n 2)
           arr v]
      (if (zero? gap)
        arr
        (recur
         (quot gap 2)
         (loop [i gap
                a arr]
           (if (>= i n)
             a
             (let [temp (nth a i)]
               (recur
                (inc i)
                (loop [j i
                       b a]
                  (if (and (>= j gap) (> (nth b (- j gap)) temp))
                    (recur (- j gap) (assoc b j (nth b (- j gap))))
                    (assoc b j temp)))))))))))