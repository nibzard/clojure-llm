(defn merge-with-defaults
  "Merge a map of defaults into a map of values.

  Values in `m` win over defaults, and nested maps should be merged recursively.
  If either input is nil, treat it as an empty map.

  Examples:
  (merge-with-defaults {:a 1} {:a 2 :b 3}) => {:a 2 :b 3}
  (merge-with-defaults {:a 1 :nested {:x 1}} {:nested {:y 2}}) => {:a 1 :nested {:x 1 :y 2}}"
  [defaults m]
  (letfn [(merge-rec [a b]
            (merge-with (fn [x y]
                          (if (and (map? x) (map? y))
                            (merge-rec x y)
                            y))
                        a b))]
    (merge-rec (or defaults {}) (or m {}))))