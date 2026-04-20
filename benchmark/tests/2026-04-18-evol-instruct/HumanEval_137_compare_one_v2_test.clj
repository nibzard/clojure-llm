(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1 :b 2 :c 3} (normalize-kw-map {"a" 1, 'b 2, :c 3})))
  (is (= {:name "Grace"} (normalize-kw-map {"name" "Ada", :name "Grace"})))
  (is (= {} (normalize-kw-map nil))))

(run-test test-variation)
