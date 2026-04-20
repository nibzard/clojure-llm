(defn sum_in_range
  "Write a cljthon function to find the sum of all odd natural numbers within the range l and r."
  [l r]
  (let [start (min l r)
        end   (max l r)
        first-odd (cond
                    (< start 1) 1
                    (odd? start) start
                    :else (inc start))
        last-odd (cond
                   (< end 1) nil
                   (odd? end) end
                   :else (dec end))]
    (if (or (nil? last-odd) (> first-odd last-odd))
      0
      (let [n (inc (quot (- last-odd first-odd) 2))]
        (/ (* n (+ first-odd last-odd)) 2)))))