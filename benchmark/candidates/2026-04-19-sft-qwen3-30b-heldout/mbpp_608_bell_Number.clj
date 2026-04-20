(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (zero? n) 1
    :else
    (let [rows (iterate
                 (fn [prev]
                   (let [n (count prev)]
                     (vec (mapv + (concat [0] prev) (concat prev [0])))))
                 [1])]
      (nth rows n))))