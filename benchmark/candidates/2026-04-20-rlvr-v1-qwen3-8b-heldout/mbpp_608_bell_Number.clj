(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (long (or n 0))]
    (if (pos? n)
      (let [rows (range 1 (inc n))]
        (reduce
          (fn [triangle k]
            (assoc-in triangle [k]
                      (reduce
                        (fn [p j]
                          (+ p (get-in triangle [(dec k) (dec j)] 0)))
                        (get-in triangle [(dec k) 0] 1)
                        (range 1 k))))
          [[0 1]]
          rows))
      1)))