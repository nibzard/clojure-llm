(defn cycpattern_check [a b]
  (if (empty? b)
    true
    (let [n (count b)
          bb (str b b)]
      (boolean (some #(clojure.string/includes? a (subs bb % (+ % n)))
                     (range n))))))