(defn min_Swaps
  "	Write a cljthon function to count minimum number of swaps required to convert one binary number represented as a string to another."
  [str1 str2]
  (let [s1 (or str1 "")
        s2 (or str2 "")]
    (if (or (not= (count s1) (count s2))
            (not-every? #{\0 \1} s1)
            (not-every? #{\0 \1} s2))
      nil
      (let [mismatches (reduce (fn [{:keys [a b]} [c1 c2]]
                                 (cond
                                   (= c1 c2) {:a a :b b}
                                   (and (= c1 \1) (= c2 \0)) {:a (inc a) :b b}
                                   (and (= c1 \0) (= c2 \1)) {:a a :b (inc b)}
                                   :else {:a a :b b}))
                               {:a 0 :b 0}
                               (map vector s1 s2))
            a (:a mismatches)
            b (:b mismatches)]
        (when (= a b)
          a)))))