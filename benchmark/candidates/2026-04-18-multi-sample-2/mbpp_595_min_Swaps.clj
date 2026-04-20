(defn min_Swaps
  "	Write a cljthon function to count minimum number of swaps required to convert one binary number represented as a string to another."
  [str1 str2]
  (let [s1 (or str1 "")
        s2 (or str2 "")]
    (if (or (not= (count s1) (count s2))
            (not (every? #{\0 \1} s1))
            (not (every? #{\0 \1} s2)))
      -1
      (let [{:keys [zero-one one-zero]}
            (reduce (fn [{:keys [zero-one one-zero] :as acc} [c1 c2]]
                      (cond
                        (= c1 c2) acc
                        (and (= c1 \0) (= c2 \1)) (assoc acc :zero-one (inc zero-one))
                        (and (= c1 \1) (= c2 \0)) (assoc acc :one-zero (inc one-zero))
                        :else acc))
                    {:zero-one 0 :one-zero 0}
                    (map vector s1 s2))]
        (if (= zero-one one-zero)
          zero-one
          -1)))))