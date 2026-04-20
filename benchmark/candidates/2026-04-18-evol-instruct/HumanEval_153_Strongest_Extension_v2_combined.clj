(defn strongest-extension
  "Given a prefix string and a possibly lazy sequence of candidate suffixes, return
  the prefix joined with the suffix that has the greatest score.

  The score of a suffix is:
  - +2 for each uppercase letter
  - +1 for each lowercase letter
  - 0 for any other character

  If two suffixes have the same score, choose the earliest one in the input.
  Ignore nil suffixes completely.

  The function must work correctly with infinite sequences by only realizing as
  much input as needed.

  Examples:
  (strongest-extension \"base\" [\"AA\" \"aB\" \"x!\"])
  ;; => \"base.AA\"

  (strongest-extension \"core\" [nil \"zz\" \"Z\" \"9Z\"])
  ;; => \"core.zz\""
  [prefix suffixes]
  (let [score (fn [s]
                (reduce (fn [n ch]
                          (cond
                            (Character/isUpperCase ch) (+ n 2)
                            (Character/isLowerCase ch) (inc n)
                            :else n))
                        0
                        s))
        best (reduce (fn [[best-s best-score] s]
                       (let [sc (score s)]
                         (if (> sc best-score)
                           [s sc]
                           [best-s best-score])))
                     [nil Long/MIN_VALUE]
                     (remove nil? suffixes))]
    (str prefix "." (first best))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "base.AA" (strongest-extension "base" ["AA" "aB" "x!"])))
  (is (= "core.zz" (strongest-extension "core" [nil "zz" "Z" "9Z"])))
  (is (= "p.A" (strongest-extension "p" (concat [nil] (repeat "a") ["A"]))))
  (is (= "x.AB" (strongest-extension "x" ["AB" "Ab" "aB"]))))

(run-test test-variation)
