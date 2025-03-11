/// A class that defines the action of onRepeatEvent in [TaskHandler].
class ForegroundTaskEventAction {
  ForegroundTaskEventAction._private({
    required this.type,
    this.interval,
    this.delay
  });

  /// Not use onRepeatEvent callback.
  factory ForegroundTaskEventAction.nothing() =>
      ForegroundTaskEventAction._private(type: ForegroundTaskEventType.nothing);

  /// Call onRepeatEvent only once.
  factory ForegroundTaskEventAction.once() =>
      ForegroundTaskEventAction._private(type: ForegroundTaskEventType.once);

  /// Call onRepeatEvent at milliseconds [interval] with a delay of [delay].
  factory ForegroundTaskEventAction.repeat(Duration interval, Duration delay) =>
      ForegroundTaskEventAction._private(
          type: ForegroundTaskEventType.repeat, interval: interval, delay: delay);

  /// The type for [ForegroundTaskEventAction].
  final ForegroundTaskEventType type;

  /// The interval(in milliseconds) at which onRepeatEvent is invoked.
  final Duration? interval;

  final Duration? delay;

  /// Returns the data fields of [ForegroundTaskEventAction] in JSON format.
  Map<String, dynamic> toJson() {
    return {
      'taskEventType': type.value,
      'taskEventInterval': interval?.inMilliseconds,
      'taskEventDelay': delay?.inMilliseconds,
    };
  }
}

/// The type for [ForegroundTaskEventAction].
enum ForegroundTaskEventType {
  /// Not use onRepeatEvent callback.
  nothing(1),

  /// Call onRepeatEvent only once.
  once(2),

  /// Call onRepeatEvent at milliseconds interval.
  repeat(3);

  const ForegroundTaskEventType(this.value);

  final int value;
}
