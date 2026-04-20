(defn sum_in_range
  "	Write a cljthon function to find the sum of all odd natural numbers within the range l and r."
  [l r]
  (let [start (min l r)
        end   (max l r)
        first-odd (cond
                    (< end 1) nil
                    (< start 1) 1
                    (odd? start) start
                    :else (inc start))]
    (if (or (nil? first-odd) (> first-odd end))
      0
      (let [n (inc (quot (- end first-odd) 2))]
        (/ (* n (+ (* 2 first-odd) (* 2 (dec n)))) 2)))))