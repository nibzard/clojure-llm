(defn has_close_elements
  [numbers threshold]
  (boolean
    (let [n (count numbers)]
      (loop [i 0]
        (when (< i n)
          (if (some (fn [j]
                      (< (Math/abs (- (nth numbers i) (nth numbers j))) threshold))
                    (range (inc i) n))
            true
            (recur (inc i))))))))
