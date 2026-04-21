(defn find_Rotations
  "	Write a cljthon function to find the minimum number of rotations (greater than 0) required to get the same string."
  [str]
  (let [n (count str)]
    (if (zero? n)
      0
      (loop [i 1]
        (if (= str (clojure.core/str (subs str i) (subs str 0 i)))
          i
          (recur (inc i)))))))