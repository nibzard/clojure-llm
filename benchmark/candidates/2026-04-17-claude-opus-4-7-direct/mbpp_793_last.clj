(defn last
  "Write a cljthon function to find the last position of an element in a sorted vector."
  [arr x]
  (let [low 0
        high (dec (count arr))]
    (loop [l low
           r high
           result -1]
      (if (> l r)
        result
        (let [mid (quot (+ l r) 2)
              mid-val (nth arr mid)]
          (cond
            (= mid-val x) (recur (inc mid) r mid)
            (< mid-val x) (recur (inc mid) r result)
            :else (recur l (dec mid) result)))))))