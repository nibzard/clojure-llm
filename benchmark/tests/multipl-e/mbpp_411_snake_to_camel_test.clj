(require '[clojure.test :refer [deftest is run-test]])

(def candidate snake_to_camel)

(deftest test-humaneval

  (is (= (candidate "android_tv") "AndroidTv"))
  (is (= (candidate "google_pixel") "GooglePixel"))
  (is (= (candidate "apple_watch") "AppleWatch"))
)

(run-test test-humaneval)