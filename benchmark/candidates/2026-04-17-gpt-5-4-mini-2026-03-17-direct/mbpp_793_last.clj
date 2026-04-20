(defn last
  "	Write a cljthon function to find the last position of an element in a sorted vector."
  [arr x]
  (let [n (count arr)]
    (loop [lo 0
           hi n
           ans -1]
      (if (< lo hi)
        (let [mid (quot (+ lo hi) 2)
              v (nth arr mid)]
          (cond
            (< v x) (recur (inc mid) hi ans)
            (> v x) (recur lo mid ans)
            :else (recur (inc mid) hi mid)))
        ans))))