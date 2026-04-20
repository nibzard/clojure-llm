(defn sum_in_range
  "	Write a cljthon function to find the sum of all odd natural numbers within the range l and r."
  [l r]
  (let [a (min l r)
        b (max l r)
        start (max 1 a)
        first-odd (if (odd? start) start (inc start))]
    (if (> first-odd b)
      0
      (let [last-odd (if (odd? b) b (dec b))
            n (inc (quot (- last-odd first-odd) 2))]
        (/ (* n (+ first-odd last-odd)) 2)))))