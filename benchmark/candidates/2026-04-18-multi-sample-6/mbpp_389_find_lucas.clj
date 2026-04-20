(defn find_lucas
  "	Write a function to find the n'th lucas number."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (zero? n) 2
    (= n 1) 1
    :else
    (loop [i 2
           a 2
           b 1]
      (if (= i n)
        (+ a b)
        (recur (inc i) b (+ a b))))))