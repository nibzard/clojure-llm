(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a "v=1" :b "v=2"}
         (format-map-values {:a 1 :b 2} #(str "v=" %))))
  (is (= {}
         (format-map-values nil pr-str))))

(run-test test-variation)
