(defn cycpattern_check
  "You are given 2 words. You need to return true if the second word or any of its rotations is a substring in the first word"
  [a b]
  (let [len (count b)]
    (if (zero? len)
      true
      (some #(clojure.string/includes? a %)
            (take len (iterate #(str (subs % 1 len) (subs % 0 1)) b))))))