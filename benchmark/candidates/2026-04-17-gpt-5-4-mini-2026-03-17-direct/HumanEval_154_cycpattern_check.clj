(defn cycpattern_check
  [a b]
  (let [s (str b b)]
    (boolean (and (seq a) (seq b) (some #(clojure.string/includes? a %) (map str (partition (count b) 1 s)))))))