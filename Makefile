.PHONY: test build fix-style show-coverage

test: fix-style
	@./gradlew check

build:
	@./gradlew build

fix-style:
	@./gradlew ktlintFormat

show-coverage:
	@sensible-browser build/reports/kover/html/index.html