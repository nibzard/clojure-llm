(defn last
  "Write a cljthon function to find the last position of an element in a sorted vector."
  [arr x]
  (loop [low 0
         high (dec (count arr))
         ans -1]
    (if (<= low high)
      (let [mid (quot (+ low high) 2)
            val (nth arr mid)]
        (cond
          (= val x) (recur (inc mid) high mid)
          (< val x) (recur (inc mid) high ans)
          :else     (recur low (dec mid) ans)))
      ans)))