(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_whitespaces)

(deftest test-humaneval

  (is (= (candidate " Google    Flutter ") "GoogleFlutter"))
  (is (= (candidate " Google    Dart ") "GoogleDart"))
  (is (= (candidate " iOS    Swift ") "iOSSwift"))
)

(run-test test-humaneval)