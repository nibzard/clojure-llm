(defn catalan_number
  "	Write a function which returns nth catalan number."
  [num]
  (let [n (if (nil? num) 0 num)]
    (if (neg? n)
      nil
      (/ (reduce *' 1 (range (inc (+ n 2)) (inc (* 2 n))))
         (reduce *' 1 (range 1 (inc n)))))))