(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (or n 0)]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (loop [i 1
             bell (vector 1)]
        (if (= i n)
          (last bell)
          (recur (inc i)
                 (conj bell
                       (reduce + (map * (reverse bell) (rest bell))))))))))