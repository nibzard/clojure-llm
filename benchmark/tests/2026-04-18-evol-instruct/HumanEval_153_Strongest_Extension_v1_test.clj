(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "my_class.AA" (strongest-extension "my_class" '("AA" "Be" "CC"))))
  (is (= "core" (strongest-extension "core" nil)))
  (is (= "pkg.ZZ9" (strongest-extension "pkg" ["a!" "ZZ9" "bB"]))))

(run-test test-variation)
