(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "base.AA" (strongest-extension "base" ["AA" "aB" "x!"])))
  (is (= "core.zz" (strongest-extension "core" [nil "zz" "Z" "9Z"])))
  (is (= "p.A" (strongest-extension "p" (concat [nil] (repeat "a") ["A"]))))
  (is (= "x.AB" (strongest-extension "x" ["AB" "Ab" "aB"]))))

(run-test test-variation)
