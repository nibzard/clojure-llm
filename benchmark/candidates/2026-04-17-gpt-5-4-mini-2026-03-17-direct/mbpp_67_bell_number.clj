(defn bell_number
  "Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (let [n (long n)]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (let [bell (atom [1])]
        (dotimes [i n]
          (let [prev (last @bell)
                next-val (reduce + (map-indexed (fn [j b] (* b (nth @bell (- i j)))) @bell))]
            (swap! bell conj next-val)))
        (last @bell)))))